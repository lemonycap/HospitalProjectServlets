package hospital.dao.impl;

import hospital.entity.diagnosis.Diagnosis;
import org.junit.Test;

import java.util.List;

public class DiagnosisDAOImplTest {

    @Test
    public void findAll() {
        List<Diagnosis> list = DiagnosisDAOImpl.findAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + list.get(i).getId());
        }
    }

    @Test
    public void findById() {
    }
}