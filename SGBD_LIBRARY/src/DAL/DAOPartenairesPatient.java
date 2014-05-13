/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BOL.Patient;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class DAOPartenairesPatient extends DAO{

    @Override
    public void insert(Object objet) {
    }

    @Override
    public void update(Object objet) {
    }

    @Override
    public void delete(Object objet) {
    }

    @Override
    public Patient selectOne(int id) {
        Patient patient = new Patient();
        return patient;
    }

    @Override
    public ArrayList<Object> selectAll(Object objet) {
        ArrayList<Object> retour = new ArrayList<Object>();
        return retour;
    }
    
}
