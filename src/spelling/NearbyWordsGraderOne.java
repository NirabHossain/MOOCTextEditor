package spelling;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;

public class NearbyWordsGraderOne {
    public static void main(String args[]) {
        int tests = 0;
        int incorrect = 0;
        String feedback = "";
        PrintWriter out;

        try {
            out = new PrintWriter("grader_output/module5.part1.out");
        	System.out.println("Running first try");
        } catch (Exception e) {
        	System.out.println("Running first catch");
            e.printStackTrace();
            return;
        }

        try {
            Dictionary d = new DictionaryHashSet();
            DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
            NearbyWords nw = new NearbyWords(d);

            List<String> d1 = nw.distanceOne("word", true);
            
/*Test 1*/
            feedback += "** Test 1: distanceOne list size... ";
            feedback += "distanceOne returned " + d1.size() + " words.\n";

/*Test 2*/            
            feedback += "** Test 2: distanceOne words returned... ";
            for (String i : d1) {
                feedback +="\n"+ i + ",";
            }

/*Test 3*/
            feedback += "\n\n\n** Test 3: distanceOne list size ";
            d1 = nw.distanceOne("word", false);
            feedback += "False returned " + d1.size() + " words.\n";
/*My tests*/
            d1 = new ArrayList<String>();
            nw.substitution("word",d1, false);
            
            feedback += "\n\n\n#############These tests are mine\n** Test ****: Substitution list size... ";
            feedback += "Substitution returned " + d1.size() + " words.\n";

            feedback += "** Test ****Double: Substitution words are... ";
            for (String i : d1) {
                feedback +="\n"+ i + ",";
            }
            feedback+="\n#################\n";
/*Test 4*/            
            d1 = new ArrayList<String>();
            
            feedback += "\n\n\n** Test 4: deletions list size... ";
            nw.deletions("makers", d1, false);
            feedback += "deletions returned " + d1.size() + " words.\n";
/*Test 5*/
            feedback += "\n\n\n** Test 5: deletions words returned... ";
            feedback += "deletions returned: ";
            for (String i : d1) {
                feedback += "\n"+i + ",";
            }
/*Test 6*/
            d1 = new ArrayList<String>();

            feedback += "\n\n\n** Test 6: insertions list size... ";
            nw.insertions("or", d1, false);
            feedback += "insertions returned " + d1.size() + " words.\n";
/*Test 7*/
            feedback += "\n\n\n** Test 7: insertions words returned... ";
            feedback += "insertions returned: ";
            for (String i : d1) {
                feedback +="\n"+ i + ",";
            }
            feedback += "Total: "+d1.size()+"\n";
            
        	System.out.println("Running second try");
        	
        	System.out.println(feedback);

            
        } catch (Exception e) {
            out.println("Runtime error: " + e);
            
        	System.out.println("Running 2nd catch");

            return;
        }

        out.println(feedback + "Tests complete. Check that everything looks right.");
        out.close();
    }
}
