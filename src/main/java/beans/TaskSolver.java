package beans;

import db.DAO;
import entity.MatrixTask;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Local
@Stateless
public class TaskSolver implements Serializable {
    @EJB
    DAO dao;

    public MatrixTask findMagicMatrix(MatrixTask m) throws SQLException {
        List<MatrixTask> magicMatrices = dao.getAllMagicMatrices();
        MatrixTask bestMatch = magicMatrices.get(0);
        int bestDist = MatrixTask.countCost(m, magicMatrices.remove(0));
        int curDist=0;

        for (MatrixTask magic: magicMatrices){
            curDist=MatrixTask.countCost(m, magic);
            if (curDist<bestDist){
                bestDist=curDist;
                bestMatch=magic;
            }
        }

        return bestMatch;
    }

    public List<String> chooseSubString(List<String> subStrings, List<String> strings){
        List<String> result = new ArrayList<>();
        nextSubString:
        for(String subString: subStrings){
            for(String string: strings){
                if (string.contains(subString)){
                    result.add(subString);
                    continue nextSubString;
                }
            }
        }
        Collections.sort(result);
        return result;
    }

}
