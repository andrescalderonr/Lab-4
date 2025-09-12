package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.SubsamplingFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubsamplingFilterTest {
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {

        SubsamplingFilter subsamplingFilter = new SubsamplingFilter();

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        Blueprint filtered = subsamplingFilter.applyFilter(bp);

        assertEquals(1, filtered.getPoints().size());
        assertEquals(0, filtered.getPoints().get(0).getX());
        assertEquals(0, filtered.getPoints().get(0).getY());

        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 10),new Point(20, 20),new Point(30, 30)};
        Blueprint bp1=new Blueprint("john", "thepaint",pts1);
        Blueprint filtered1 = subsamplingFilter.applyFilter(bp1);

        assertEquals(2, filtered1.getPoints().size());
        assertEquals(0, filtered1.getPoints().get(0).getX());
        assertEquals(0, filtered1.getPoints().get(0).getY());
        assertEquals(20, filtered1.getPoints().get(1).getX());
        assertEquals(20, filtered1.getPoints().get(1).getY());

    }
}
