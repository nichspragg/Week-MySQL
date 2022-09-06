package projects.service;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.*;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();
	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}

	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(
				() -> new NoSuchElementException
				("Project with project ID=" + projectId + " does not exist."));
		
/*
		try {
		Project filler = new Project();
		filler = projectDao.fetchProjectById(projectId);
		boolean fillerId = filler.getProjectId() != null;
		System.out.println(fillerId);
		if(fillerId) {
			return filler;
		} else { 
			System.out.println("Project with project ID= " + projectId + " does not exist.");
			throw new NoSuchElementException();
		}
		} catch (Exception e) {
			throw new DbException(e); }
*/		
	}

}
