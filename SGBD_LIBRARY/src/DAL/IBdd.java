/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.util.ArrayList;

/**
 *
 * @author JOHNNY
 */
public interface IBdd
{
    public void insert(Object objet);
    public void update(Object objet);
    public void delete(Object objet);
    public Object selectOne(int id);
    //public ArrayList<Object> selectAll(Object objet);
}
