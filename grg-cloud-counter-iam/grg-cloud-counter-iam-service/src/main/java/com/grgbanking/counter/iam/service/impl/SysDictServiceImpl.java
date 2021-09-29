package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.iam.api.entity.SysDictEntity;
import com.grgbanking.counter.iam.dao.SysDictDao;
import com.grgbanking.counter.iam.service.SysDictItemService;
import com.grgbanking.counter.iam.service.SysDictService;
import com.grgbanking.counter.iam.vo.DictWithItemVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {


    private final RedisTemplate redisTemplate;

    @Autowired
    private SysDictDao dictDao;

    @Autowired
    private SysDictItemService dictItemService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDictEntity> page = this.page(
                new Query<SysDictEntity>().getPage(params),
                new QueryWrapper<SysDictEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Map<String, String>>> listDictWithItem() {

        Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DICT_DEFAULT_KEY );
        if (codeObj != null) {
//            log.info("字典缓存已存在:{}，{}", "dict", codeObj);
            log.info("字典缓存已存在");
            return (List<Map<String, Map<String, String>>>) codeObj;
        }
        //将dict写入缓存中
        saveDictInRedis();
        Object obj = redisTemplate.opsForValue().get(CacheConstants.DICT_DEFAULT_KEY );

        return (List<Map<String, Map<String, String>>>) obj;
    }

    @Override
    public boolean saveDictAndItem(SysDictEntity sysDict) {
        boolean save = false;
        if (sysDict!=null) {
            save = this.save(sysDict);
            //更新redis
            saveDictInRedis();
        }
        return save;

    }

    @Override
    public List<DictWithItemVo> getDictByType(SysDictEntity sysDict) {
        if (sysDict.getType()!=null) {
            List<DictWithItemVo> dictAndItem = dictDao.getDictAndItem();
            List<DictWithItemVo> collect = dictAndItem.stream().filter(dict ->
                    dict.getType().equals(sysDict.getType())
            ).collect(Collectors.toList());
            return collect;
        }
        else{
            return null;
        }

    }

    @Override
    public void removeDictById(List<Long> asList) {
        //TODO 字典可能绑定了其他字段 需判断
        baseMapper.deleteBatchIds(asList);
        saveDictInRedis();
    }

    @Override
    public int updateDictById(SysDictEntity sysDict) {
        int update = baseMapper.update(sysDict, null);
        if (update==1) {
            saveDictInRedis();
        }
        return update;
    }

    public void saveDictInRedis(){
        //获取根据type字段保存字典信息
        List<DictWithItemVo> dictAndItem = dictDao.getDictAndItem();
        //1级list
        List<Map<String, Map<String, String>>> collect = dictAndItem.stream().map((dictWithItemVo) -> {
            //二级map
            Map<String, Map<String, String>> dictItemMap = new HashMap<>();
            //三级map
            Map<String, String> valueLabel = new HashMap<>();
            valueLabel.put(dictWithItemVo.getValue(), dictWithItemVo.getLabel());
            dictItemMap.put(dictWithItemVo.getType(), valueLabel);

            return dictItemMap;
        }).collect(Collectors.toList());
        redisTemplate.opsForValue().set(CacheConstants.DICT_DEFAULT_KEY, collect);
        log.info("字典存入缓存成功");
    }
}
