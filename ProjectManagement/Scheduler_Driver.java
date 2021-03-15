package ProjectManagement;
//package Trie;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import Trie.*;
import PriorityQueue.*;
import RedBlack.*;
import java.util.HashMap;
public class Scheduler_Driver extends Thread implements SchedulerInterface {


    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        scheduler_driver.execute();
    }

    public void execute() throws IOException {


        File file;
        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found");
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                	
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }
    ArrayList<String> alist=new ArrayList<String>();
    int no_of_job=0;
    Trie<Project> trie=new Trie();
    MaxHeap<Job> mh=new MaxHeap();
    ArrayList<Job> al_job=new ArrayList<Job>();
    ArrayList<Job> un_comp=new ArrayList<Job>();
    ArrayList<User> userlist=new ArrayList<User>();
    @Override
    public void run() {
        // till there are JOBS
        schedule();
    }


    @Override
    public void run_to_completion() {
    	
    	Job j=null;
    	while((j=mh.extractMax())!=null) {
    		System.out.println("Running code");
    		int y=no_of_job;
    		no_of_job--;
    		System.out.println("	"+"Remaining jobs: "+y);
    		TrieNode t=trie.search(j.project);
    		Project p=(Project)t.getValue();
        	int budget=p.budget;
    		int x=budget-j.runTime;
    		System.out.println("	"+"Executing: "+j.name+" from: "+j.project);
    		if(x>0) {
        		j.Status="FINISHED";
            	System.out.println("	"+"Project: "+j.project+" budget remaining: "+x);
            	p.budget=x;
            	System.out.println("system cycle completed");
        		//break;
    		}
    		else {
    			System.out.println("	Un-sufficient budget.");
    			un_comp.add(j);
   			run_to_completion();
    			break;
    		}
    }
//we have to make all jobs excuted.
    }

    @Override
    public void handle_project(String[] cmd) {
    	System.out.println("Creating project");
    	String name=cmd[1];
    	int prior=Integer.parseInt(cmd[2]);
    	int budget=Integer.parseInt(cmd[3]);
    	Project p=new Project(name,prior,budget);
    	trie.insert(name,p);
    	//add in trie.
    }

    @Override
    public void handle_job(String[] cmd) {
    	//trie.print();
     	System.out.println("Creating job");
    	no_of_job++;
    	String name=cmd[1];
    	String project=cmd[2];
    	String user=cmd[3];
    	boolean is_user=false;
    	for(int i=0;i<alist.size();i++) {
    		if(alist.get(i)!=null && alist.get(i).equals(user)) {
    			is_user=true;
    		}
    	}
    	TrieNode t=trie.search(project);
    	//System.out.println(project);
    	if(t!=null && is_user) {
       
    		Project p=(Project)t.getValue();
        	int budget=p.priority;
        	int runTime=Integer.parseInt(cmd[4]);
        	Job j=new Job(name,project,runTime,budget);
        	j.Status="NOT FINISHED";
        	al_job.add(j);
        	mh.insert(j);
    	}
    	else if(!is_user) {
    		System.out.println("No such user exists: "+user);no_of_job--;}
    	else if(t==null) {
    		System.out.println("No such project exists. "+project);no_of_job--;}
    	//add in heap.//find the budget from trie of projects and send it into this.
    }

    @Override
    public void handle_user(String name) {
    	System.out.println("Creating user");
    	//add in arraylist.
    	User u=new User(name);
    	userlist.add(u);
    	alist.add(name);
    }

    @Override
    public void handle_query(String key) {
    	System.out.println("Querying");
    	boolean isjob=false;
    	Job j=null;
    	for(int i=0;i<al_job.size();i++) {
    		if(al_job.get(i)!=null && al_job.get(i).name.equals(key)) {
    			j=al_job.get(i);
    			isjob=true;
    			break;
    		}
    	}
    	if(!isjob) {
    		System.out.println(key+": "+"NO SUCH JOB");
    	}
    	else {
    		System.out.println(j.name+": "+j.Status);
    	}
    	//return status of the job;
    }

    @Override
    public void handle_empty_line() {
    	//donot send budget to 
    	System.out.println("Running code");
    	Job j=null;int y=no_of_job;
    	System.out.println("	"+"Remaining jobs: "+y);
    	while(true) {
    		j=mh.extractMax();
    		no_of_job--;
    		TrieNode t=trie.search(j.project);
    		Project p=(Project)t.getValue();
        	int budget=p.budget;
    		int x=budget-j.runTime;
    		System.out.println("	"+"Executing: "+j.name+" from: "+j.project);
    		if(x>0) {
        		j.Status="FINISHED";
            	System.out.println("	"+"Project: "+j.project+" budget remaining: "+x);
            	p.budget=x;
        		break;
    		}
    		else {
    			System.out.println("	Un-sufficient budget.");
    			un_comp.add(j);
    			//handle_empty_line();
    			//break;
    		}
    		
    	}
    	System.out.println("Execution cycle completed");
    	//extractmax.
    }

    @Override
    public void handle_add(String[] cmd) {
    	for(int i=0;i<un_comp.size();i++) {
    		if(un_comp.get(i)!=null ) {
    			System.out.println(un_comp.get(i).name);
    		}
    	}
    	String project=cmd[1];
    	int budget=Integer.parseInt(cmd[2]);
    	TrieNode t=trie.search(project);
    	Project p=(Project)t.getValue();
    	p.budget=p.budget+budget;
    	for(int i=0;i<un_comp.size();i++) {
    		if(un_comp.get(i)!=null && un_comp.get(i).project.equals(project)) {
    			Job j=un_comp.get(i);
    			mh.insert(j);
    			no_of_job++;
    			un_comp.set(i, null) ;   		}
    	}
    	//ADD IITD.CS.ML.ICML 60
    	//increase budget of project.
    }

    @Override
    public void print_stats() {
    	//is job completed or not.
    }

    @Override
    public void schedule() {
    	
    }
}
