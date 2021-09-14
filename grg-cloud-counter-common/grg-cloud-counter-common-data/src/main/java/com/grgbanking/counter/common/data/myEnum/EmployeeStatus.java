package com.grgbanking.counter.common.data.myEnum;

/**
 * @author: Ye Kaitao
 * @create: 2021-09-14
 * 坐席状态(1、签入：用户登录；
 * 2、就绪：工作人员进入待办业务状态；
 * 3、办理：工作人员业务办理中状态；
 * 4、小憩：人员暂时离开；
 * 5、签出：用户坐席离线；)
 */
public enum EmployeeStatus {
    /**
     *
     */
    SIGNIN("签入", 1),
    READY("就绪", 2),
    HANDLING("办理", 3),
    REST("小憩", 4),
    SIGNOUT("签出", 5);

    private String msg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    EmployeeStatus(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }
}
