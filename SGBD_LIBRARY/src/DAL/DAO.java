/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;


import java.util.ArrayList;
import DAL.ConnectionOracle;
import java.sql.Connection;
/**
 *
 * @author JOHNNY
 */
public abstract class DAO
{
    public Connection connection=ConnectionOracle.getInstance("jdbc:oracle:thin:@svroracle.montpellier.epsi.fr:1521","BD_MM","mmACDS2014");
    public abstract void insert(Object objet);
    public abstract void update(Object objet);
    public abstract void delete(Object objet);
    public abstract Object selectOne(int id);
    public abstract ArrayList<Object> selectAll(Object objet);
}
