package com.example.test4listview;

import java.io.Serializable;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-5-29
 */

public class SearchConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    public enum AddressType {
        FromPoint, ToPoint
    }

    public AddressType mAddressType = AddressType.ToPoint;
}
