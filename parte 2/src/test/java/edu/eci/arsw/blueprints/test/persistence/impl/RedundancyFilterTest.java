package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.impl.RedundancyFilter;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedundancyFilterTest{
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {

        RedundancyFilter redundancyFilter = new RedundancyFilter();

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        Blueprint filtered = redundancyFilter.applyFilter(bp);

        assertEquals(2, filtered.getPoints().size());
        assertEquals(0, filtered.getPoints().get(0).getX());
        assertEquals(0, filtered.getPoints().get(0).getY());
        assertEquals(10, filtered.getPoints().get(1).getX());
        assertEquals(10, filtered.getPoints().get(1).getY());

        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 10),new Point(10, 10),new Point(10, 10)};
        Blueprint bp1=new Blueprint("john", "thepaint",pts1);
        Blueprint filtered1 = redundancyFilter.applyFilter(bp1);

        assertEquals(2, filtered1.getPoints().size());
        assertEquals(0, filtered1.getPoints().get(0).getX());
        assertEquals(0, filtered1.getPoints().get(0).getY());
        assertEquals(10, filtered1.getPoints().get(1).getX());
        assertEquals(10, filtered1.getPoints().get(1).getY());

    }
}
