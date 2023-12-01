package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import net.sf.jasperreports.engine.util.DisplayValue;

public class ComboBoxDAO {
    private Connection connection;

    public ComboBoxDAO(Connection connection) {
        this.connection = connection;
    }

    public DefaultComboBoxModel layDuLieuCBB(String bang, String tenCotHienThi, String tenCotMa) {
        String cauTruyVan = "SELECT * FROM " + bang;
        DefaultComboBoxModel cbbModel = new DefaultComboBoxModel();

        try (PreparedStatement preparedStatement = connection.prepareStatement(cauTruyVan)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ten = rs.getString(tenCotHienThi);
                String ma = rs.getString(tenCotMa);
                cbbModel.addElement(new DisplayValue(ten, ma));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cbbModel;
    }

    public List<DisplayValue> layDuLieu(String bang, String tenCotHienThi, String tenCotMa) {
        String cauTruyVan = "SELECT * FROM " + bang;
        List<DisplayValue> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(cauTruyVan)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ten = rs.getString(tenCotHienThi);
                String ma = rs.getString(tenCotMa);
                result.add(new DisplayValue(ten, ma));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    // New method
    public DefaultComboBoxModel layDuLieuCBB2(String bang, String tenCotHienThi, String tenCotMa) {
        String cauTruyVan = "SELECT * FROM " + bang;
        DefaultComboBoxModel cbbModel = new DefaultComboBoxModel();

        try (PreparedStatement preparedStatement = connection.prepareStatement(cauTruyVan)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ten = rs.getString(tenCotHienThi);
                String ma = rs.getString(tenCotMa);
                cbbModel.addElement(new DisplayValue(ten, ma));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cbbModel;
    }
}
