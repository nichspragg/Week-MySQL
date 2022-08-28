DROP TABLE project_category;
DROP TABLE category;
DROP TABLE step;
DROP TABLE material;
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

CREATE TABLE material (
	material_id INT NOT NULL PRIMARY KEY,
    project_id INT NOT NULL,
    material_name VARCHAR(128) NOT NULL,
    num_required INT,
    cost DECIMAL(7,2),
    FOREIGN KEY (project_id)
		REFERENCES project(project_id)
);

CREATE TABLE step (
	step_id	 INT NOT NULL PRIMARY KEY,
    project_id INT NOT NULL,
    step_text TEXT NOT NULL,
    step_order INT NOT NULL,
    FOREIGN KEY (project_id)
		REFERENCES project(project_id)
);

CREATE TABLE category (
	category_id INT NOT NULL PRIMARY KEY,
    category_name VARCHAR(128) NOT NULL
);

CREATE TABLE project_category (
	project_id INT NOT NULL,
    FOREIGN KEY (project_id)
		REFERENCES project(project_id),
	category_id INT NOT NULL,
    FOREIGN KEY (category_id)
		REFERENCES category(category_id),
	UNIQUE(project_id,category_id)
);