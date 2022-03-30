package DAO;

import Info.UserInfo;

import java.util.List;
// 数据库基本操作
public interface DataBaseDAO {
    List<UserInfo> FindInfoBY(UserInfo userInfo) throws Exception;
    List<UserInfo> FindInfoAll() throws Exception;
    boolean addInfo(UserInfo userInfo) throws Exception;
}
