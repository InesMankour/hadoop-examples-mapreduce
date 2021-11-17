package com.opstty;

import com.opstty.job.*;
import org.apache.hadoop.util.ProgramDriver;
import com.opstty.job.DistinctDistricts;
import com.opstty.job.TallestTree;
import com.opstty.job.WordCount;
public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("wordcount", WordCount.class,
                    "wordcount.");
            programDriver.addClass("distinctDistricts", DistinctDistricts.class,
                    "shows the distinct districts containing trees.");
            programDriver.addClass("species", Species.class,
                    "shows all the species of the dataset");
            programDriver.addClass("speciesCount", SpeciesCount.class,
                    "count the number of trees per species");

            programDriver.addClass("tallestTree", TallestTree.class,
                    "shows the tallest tree per species");

            programDriver.addClass("heightSort", HeightSort.class,
                    "all the trees sorted by height");

            programDriver.addClass("oldestTree", OldestTree.class,
                    "shows the district and the age of the oldest tree of the dataset");


            exitCode = programDriver.run(argv);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.exit(exitCode);
    }
}
