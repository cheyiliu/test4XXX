package com.didi.biz.editor.container;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public interface ICreatorItem {
    boolean isItemEnabled();

    void setItemEnabled(boolean enable);

    void setLeftSubject(String subject);

    void setLeftSubjectDes(String des);

    void setRightIcon(int iconId);

    void setRightInputHint(String hint);

    void setRightInputContent(String content);

    void setRightInputContent(String content1, String content2);
}
