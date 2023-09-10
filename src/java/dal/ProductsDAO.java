package dal;

import model.Quiz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    List<Quiz> list = new ArrayList<>();

    public List<Quiz> getAll() {
        try {
            String strSelect = "select * from quiz";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//    public List<Products> getByName(String na) {
//        try {
//            String strSelect = "select * from Products where Name like '%" + na + "%'";
//            stm = connection.prepareStatement(strSelect);
//            stm.executeQuery();
//            while (rs.next()) {
//                Products em = new Products(rs.getInt("Id"), rs.getInt("Catalogy_Id"), rs.getString("Name"), rs.getString("Note"), rs.getString("Images"));
//                list.add(em);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return list;
//    }
//
//    public void addProduct(int caId, String name, String note, String img) {
//        try {
//            String strSelect = "INSERT INTO [dbo].[Products]\n"
//                    + "           ([Catalogy_Id]\n"
//                    + "           ,[Name]\n"
//                    + "           ,[Note]\n"
//                    + "           ,[Images])\n"
//                    + "     VALUES(?,?,?,?)";
//            stm = connection.prepareStatement(strSelect);
//            stm.setInt(1, caId);
//            stm.setString(2, name);
//            stm.setString(3, note);
//            stm.setString(4, img);
//            stm.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//
//    public void deleteProduct(int id) {
//        try {
//            String sql = "DELETE FROM [dbo].[Products] WHERE Id = " + id;
//            stm = connection.prepareStatement(sql);
//            stm.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public Products getProductById(int id) {
//        String sql = "SELECT * FROM [dbo].[Products] where id = ?";
//        try {
//            stm = connection.prepareStatement(sql);
//            stm.setInt(1, id);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                return new Products(rs.getInt("Id"), rs.getInt("Catalogy_Id"), rs.getString("Name"), rs.getString("Note"), rs.getString("Images"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//
//    public void updateProduct(int caId, String name, String note, String img, int id) {
//        String sql = "UPDATE [dbo].[Products]\n"
//                + "   SET [Catalogy_Id] = ?\n"
//                + "      ,[Name] = ?\n"
//                + "      ,[Note] = ?\n"
//                + "      ,[Images] = ?\n"
//                + " WHERE id = ?";
//
//        try {
//            stm = connection.prepareStatement(sql);
//            stm.setInt(1, caId);
//            stm.setString(2, name);
//            stm.setString(3, note);
//            stm.setString(4, img);
//            stm.setInt(5, id);
//            stm.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public static void main(String[] args) {
        ProductsDAO a = new ProductsDAO();
        List<Quiz> lsit = a.getAll();
        for (Quiz o : lsit) {
            System.out.println(o);
        }
    }
}
