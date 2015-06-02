package com.didi.biz.editor.container;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public interface IAction {
    /**
     * 结果集验证
     * @return true验证通过，false验证失败
     */
    public boolean verify();

    /**
     * 触发输入
     */
    public void requestInput();
}
