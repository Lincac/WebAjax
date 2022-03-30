package DAO;

import Info.UserInfo;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DataBaseDAOImp implements DataBaseDAO{
    private Connection cnn = null;
    public DataBaseDAOImp(Connection connection){ this.cnn = connection;}

    @Override
    public List<UserInfo> FindInfoBY(UserInfo userInfo) throws SQLException {
        int count=0;
        List<UserInfo> userInfoList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("identify",userInfo.getIdentify());
        map.put("name",userInfo.getName());
        map.put("sex", userInfo.getSex());
        map.put("province",userInfo.getProvince());
        map.put("city",userInfo.getCity());
        map.put("susp",userInfo.getSusp());
        map.put("diag",userInfo.getDiag());
        map.put("date",userInfo.getDate());
        for (String key : map.keySet()) {
            String val = map.get(key);
            if (!val.equals("")) {
                count++;
            }
        }
        String sql = "SELECT * FROM info";
        if (count != 0){
            sql+=" WHERE ";
        }
        for (String key : map.keySet()) {
            String val = map.get(key);
            if (!val.equals("")&& count!=0) {
                sql+=key+"="+"'"+val +"'";
                count--;
                if (count!=0){
                    sql+= " AND ";
                }
            }
        }
        Statement stmt = cnn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            UserInfo uu = new UserInfo();
            uu.setIdentify(rs.getString(1));
            uu.setName(rs.getString(2));
            uu.setCollege(rs.getString(3));
            uu.setSex(rs.getString(4));
            uu.setProvince(rs.getString(5));
            uu.setCity(rs.getString(6));
            uu.setTemp(rs.getString(7));
            uu.setIs_back(rs.getString(8));
            uu.setSusp(rs.getString(9));
            uu.setDiag(rs.getString(10));
            uu.setDate(rs.getString(11));
            userInfoList.add(uu);
        }
        stmt.close();
        rs.close();
        return userInfoList;
    }

    @Override
    public List<UserInfo> FindInfoAll() throws SQLException {
        List<UserInfo> userInfoList = new ArrayList<>();
        String sql = "SELECT * FROM info";
        PreparedStatement stmt = cnn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            UserInfo uu = new UserInfo();
            uu.setIdentify(rs.getString(1));
            uu.setName(rs.getString(2));
            uu.setCollege(rs.getString(3));
            uu.setSex(rs.getString(4));
            uu.setProvince(rs.getString(5));
            uu.setCity(rs.getString(6));
            uu.setTemp(rs.getString(7));
            uu.setIs_back(rs.getString(8));
            uu.setSusp(rs.getString(9));
            uu.setDiag(rs.getString(10));
            uu.setDate(rs.getString(11));
            userInfoList.add(uu);
        }
        rs.close();
        stmt.close();
        return userInfoList;
    }

    @Override
    public boolean addInfo(UserInfo userInfo) throws Exception {
        String sql = "INSERT INTO info values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = cnn.prepareStatement(sql);
        stmt.setString(1,userInfo.getIdentify());
        stmt.setString(2,userInfo.getName());
        stmt.setString(3,userInfo.getCollege());
        stmt.setString(4,userInfo.getSex());
        stmt.setString(5,userInfo.getProvince());
        stmt.setString(6,userInfo.getCity());
        stmt.setString(7,userInfo.getTemp());
        stmt.setString(8,userInfo.getIs_back());
        stmt.setString(9,userInfo.getSusp());
        stmt.setString(10,userInfo.getDiag());
        Date date = new Date();
        stmt.setDate(11, new java.sql.Date(date.getTime()));
        int item = stmt.executeUpdate();
        if (item == 1){
            stmt.close();
            return true;
        }
        return false;
    }

}
