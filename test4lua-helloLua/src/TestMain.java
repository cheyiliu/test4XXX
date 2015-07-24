/**
 * Class description
 *
 * @author hsy
 * @since 2015-7-23
 */

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        LuaState L = LuaStateFactory.newLuaState();
        L.openLibs();
        L.LdoFile("hello.lua");
    }
}