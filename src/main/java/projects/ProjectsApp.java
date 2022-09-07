package projects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {

private Scanner scanner = new Scanner(System.in);

// @formatter:off
		private List<String> operations = List.of( "1) Add a project", "2) List projects", "3) Select a project" );
// @formatter:on

private ProjectService projectService = new ProjectService();
private Project curProject = new Project();
private List<Integer> projectNums = new ArrayList <>();

public static void main(String[] args) {
		
	new ProjectsApp().processUserSelections();
	
		
		
	} //end of main

private void processUserSelections() {
	boolean done = false;
	while(!done) {
		try {
			int selection = getUserSelection();
			
			switch(selection) {
			case -1:
				done = exitMenu();
				break;
			case 1:
				createProject();
				break;
			case 2:
				listProjects();
				break;
			case 3:
				selectProject();
				break;
			default:
				System.out.println("\n" + selection + " is not a valid selection. Try again.");
			
			} //end of switch
		}
		catch(Exception e){
			System.out.println("\nError: " + e + " Try again.");
			e.printStackTrace();
			}
		} //end of while
	}
private void selectProject() {
	listProjects();
	Integer projectId = getIntInput("Enter a project ID to select a project");
//	curProject = null;
	if(projectNums.contains(projectId)) {
		curProject = null;
		curProject = projectService.fetchProjectById(projectId); }
	else { System.out.println("Project with project ID=" + projectId + " does not exist.");	}
	}

private void listProjects() {
	List<Project> projects = projectService.fetchAllProjects();
		
	System.out.println("\nProjects:");
	
	projects.forEach(project -> System.out.println("  " + project.getProjectId() + ": " + project.getProjectName()));
	projects.forEach(project -> projectNums.add(project.getProjectId() ));
	}
private void createProject() {
	String projectName = getStringInput("Enter the project name");
	BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
	BigDecimal actualHours = getDecimalInput("Enter the actual hours");
	Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		while(difficulty<1 || difficulty>5) {
			difficulty = getIntInput("Project difficulty must be (1-5). Please re-enter"); }
	String notes = getStringInput("Enter the project notes");
	
	Project project = new Project();
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
	}
private BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt);
	if(Objects.isNull(input)) {
		return null; }
	try {
		return new BigDecimal(input).setScale(2);
		}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid decimal number.");
		}
	}
private boolean exitMenu() {
	System.out.println("The end is neigh. Goodbye!");
	return true;
	}
private int getUserSelection() {
	printOperations();
	Integer input = getIntInput("Enter a menu selection");
	return Objects.isNull(input) ? -1 : input;
	}
private void printOperations() {
	System.out.println("\nThese are the available selections. Press the Enter key to quit:");
	operations.forEach(line -> System.out.println("  " + line));
	
	if(curProject.getProjectId()==null) {
		System.out.println("\nYou are not working with a project."); }
	else {System.out.println("\nYou are working with project: " + curProject);}
	}
private Integer getIntInput(String prompt) {
	String input = getStringInput(prompt);
	if(Objects.isNull(input)) {
		return null; }
	try {
		return Integer.valueOf(input);
		}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid number.");
		}
	}
private String getStringInput(String prompt){
	System.out.print(prompt + ": ");
	String input = scanner.nextLine();
	return input.isBlank() ? null : input.trim();
	}
} //end of all
