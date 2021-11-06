import java.util.*;
import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class main {

    

    public static void main(String[] args) {

        //Object[] alphabet = new Object[10];
        Object[] alphabet = {"a", "b"};

        ArrayList<State> D1states = new ArrayList<State>();
        ArrayList<State> D2states = new ArrayList<State>();

        ArrayList<Transition> D1Transitions = new ArrayList<Transition>();
        ArrayList<Transition> D2Transitions = new ArrayList<Transition>();
        
        State q2 = new State("q2", false, false);

        State D1initial = new State("q1", true, false);
        State D1final = new State("q3", false, true);



        D1states.add(q2);
        D1states.add(D1final);
        D1states.add(D1initial);
        

        Transition q0A = new Transition("q0", "a", "q1");
        Transition q0B = new Transition("q0", "b", "q2");
        Transition q1A = new Transition("q1", "a", "q1");
        Transition q1B = new Transition("q1", "b", "q1");
        Transition q2A = new Transition("q2", "a", "q2");
        Transition q2B = new Transition("q2", "b", "q2");

        D1Transitions.add(q0A);
        D1Transitions.add(q0B);
        D1Transitions.add(q1A);
        D1Transitions.add(q1B);
        D1Transitions.add(q2A);
        D1Transitions.add(q2B);

        //State[] D2states = {"r1", "r2"};
        State D2initial = new State("r1", true, false);
        State D2final = new State("r2", false, true);


        D2states.add(D2initial);
        D2states.add(D2final);

        Transition r0A = new Transition("r0", "a", "r0");
        Transition r0B = new Transition("r0", "b", "r1");
        Transition r1A = new Transition("r1", "a", "r1");
        Transition r1B = new Transition("r1", "a", "r0");


        D2Transitions.add(r0A);
        D2Transitions.add(r0B);
        D2Transitions.add(r1A);
        D2Transitions.add(r1B);

        int numUnionStates = (D1states.size()*D2states.size());
        ArrayList<State> unionStates = new ArrayList<State>();


        for(int q = 0; q < D1states.size(); q++){ // loop q
            for (int r = 0; r < D2states.size(); r++) { // loop r
            
                String unionName;
                boolean unionStart = false;
                boolean unionAccept = false;

                unionName = D1states.get(q).stateID + D2states.get(r).stateID; // get UnionName
            
                if (D1states.get(q).isStart && D2states.get(r).isStart) {
                    unionStart = true;
                }

                if (D1states.get(q).isAccept || D2states.get(r).isAccept) {
                    unionAccept = true;
                }
                
                unionStates.add(new State(unionName, unionStart, unionAccept));
                
            }

            
        }


        ArrayList<Transition> unionTransitions = new ArrayList<Transition>();
        for (int qt = 0; qt < D1Transitions.size(); qt ++) {
            for (int rt = 0; rt < D2Transitions.size(); rt ++) {
                boolean matching = false;
                if (D1Transitions.get(qt).alpha.equals(D2Transitions.get(rt).alpha)) {
                    matching = true;
                }

                if (matching) {
                    String unionSrc = D1Transitions.get(qt).source + D2Transitions.get(rt).source;
                    String unionDest = D1Transitions.get(qt).dest + D2Transitions.get(rt).dest;

                    unionTransitions.add(new Transition(unionSrc, D1Transitions.get(qt).alpha, unionDest));
                }
            }
        }

        
        for (Transition r : unionTransitions){
            System.out.println(r.toString());
        }
        
        //System.out.println("Hello world");

        JSONParser parser = new JSONParser();
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try(FileReader reader = new FileReader("DFA1.json")) {
            Object obj = parser.parse(reader);
            JSONObject objJSON = (JSONObject) obj;
            
            JSONArray states = (JSONArray)objJSON.get("alphabet");

            Iterator iterator = states.iterator();
            
            int i = 0;
            while (iterator.hasNext()) {
                alphabet[i] = iterator.next();
                i++;
            }
            
            // for (Object v : alphabet){
            //     System.out.println(v);
            // }

 

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
             e.printStackTrace();
        }
    }


        //     in = new FileInputStream("DFA1.json");
        //     out = new FileOutputStream("UnionDFA.json");

        //     int c;
        //     while ((c = in.read()) != -1) {
        //        out.write(c);
        //    }

        // } catch (FileNotFoundException e){
        //     System.out.println("File opening Error");
        // } catch (IOException e ){
        //     System.out.println("File IO");
        // }

       
    }





