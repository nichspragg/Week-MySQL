DROP TABLE project_category;
DROP TABLE material;
DROP TABLE step;
DROP TABLE category;
DROP TABLE project;


CREATE TABLE project (
	project_id INT NOT NULL AUTO_INCREMENT,
    project_name VARCHAR(128) NOT NULL,
    estimated_hours DECIMAL(7,2),
    actual_hours DECIMAL(7,2),
    difficulty INT,
    notes TEXT,
    PRIMARY KEY (project_id)
);

CREATE TABLE category (
	category_id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (category_id),
    category_name VARCHAR(128) NOT NULL
);

CREATE TABLE step (
	step_id	 INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (step_id),
    project_id INT NOT NULL,
    step_text TEXT NOT NULL,
    step_order INT NOT NULL,
    FOREIGN KEY (project_id)
		REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE material (
	material_id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (material_id),
    project_id INT NOT NULL,
    material_name VARCHAR(128) NOT NULL,
    num_required INT,
    cost DECIMAL(7,2),
    FOREIGN KEY (project_id)
		REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE project_category (
	project_id INT NOT NULL,
    FOREIGN KEY (project_id)
		REFERENCES project(project_id) ON DELETE CASCADE,
	category_id INT NOT NULL,
    FOREIGN KEY (category_id)
		REFERENCES category(category_id) ON DELETE CASCADE,
	UNIQUE(project_id,category_id)
);

-- add data

INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) 
	VALUES ('Hang picture', .1, .2, 1, 'use panel nails or smaller');
INSERT INTO material (project_id, material_name, num_required, cost) 
	VALUES (3, 'panel nail', 1, 1);
INSERT INTO material (project_id, material_name, num_required, cost) 
	VALUES (3, 'level', 1, 8);
INSERT INTO step (project_id, step_text, step_order) 
	VALUES (3, 'Insert nail into wall', 1);
INSERT INTO step (project_id, step_text, step_order) 
	VALUES (3, 'Place back of picture on the nail, use hanging grooves if present', 2);
INSERT INTO step (project_id, step_text, step_order) 
	VALUES (3, 'Gently lower level down to top of frame. Adjust as needed', 3);
INSERT INTO category (category_id, category_name) 
	VALUES (2, 'Decor');
INSERT INTO project_category (project_id, category_id) 
	VALUES (3, 2);


