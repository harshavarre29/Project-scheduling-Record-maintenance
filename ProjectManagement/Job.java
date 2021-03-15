package ProjectManagement;

public class Job implements Comparable<Job> {
//have two ststus
//stored in priority queue;
	String Status;
	String name;
	String project;
	int runTime;
	int Priority;
	Job(String name,String project,int runTime,int budget){
		this.name=name;
		this.project=project;
		this.runTime=runTime;
		this.Priority=budget;
	}
    @Override
    public int compareTo(Job job) {
    	if(job.Priority-this.Priority!=0)
        return job.Priority-this.Priority;
    	else {
    		return -job.name.compareTo(this.name);
    	}
    }
}