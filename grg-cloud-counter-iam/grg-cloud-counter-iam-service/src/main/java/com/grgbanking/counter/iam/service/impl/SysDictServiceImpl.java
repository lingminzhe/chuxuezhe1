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
import java.util.concurrent.TimeUnit;
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

        List<DictWithItemVo> dictAndItem = dictDao.getDictAndItem();
        List<Map<String, Map<String, String>>> collect = dictAndItem.stream().map((dictWithItemVo) -> {
//                一级key为grgbanking.counter.dict
//                二级key 为type  value 为 (value,label)
//                Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);

            Map<String, Map<String, String>> dictItemMap = new HashMap<>();
            Map<String, String> valueLabel = new HashMap<>();
            valueLabel.put(dictWithItemVo.getValue(), dictWithItemVo.getLabel());
            dictItemMap.put(dictWithItemVo.getType(), valueLabel);

            return dictItemMap;
        }).collect(Collectors.toList());

        Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DICT_DEFAULT_KEY );
        if (codeObj != null) {
            log.info("字典缓存已存在:{}，{}", "dict", codeObj);
            //TODO 清理缓存 再更新
//            return Resp.success(Boolean.FALSE, "验证码发送过频繁");
            return collect;
        }

        redisTemplate.opsForValue().set(CacheConstants.DICT_DEFAULT_KEY, collect, 24*60*60 , TimeUnit.SECONDS);
        log.info("字典存入缓存成功");



        return collect;
    }
}
