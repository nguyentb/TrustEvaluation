CREATE TABLE entity (
	entity_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	entity_name varchar(50),
	ability double unsigned CHECK (ability >= 0),
	integrity double unsigned CHECK (integrity >= 0),
	benevolence double unsigned CHECK (benevolence >= 0),
	reputation double unsigned CHECK (reputation >= 0),
	entity_timestamp TIMESTAMP
);

CREATE TABLE interaction (
	interaction_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	source_id INT NOT NULL,
	destination_id INT NOT NULL,
	interaction_value double unsigned CHECK (i_value >= 0),
	interaction_timestamp TIMESTAMP,
	
	FOREIGN KEY (source_id) references entity(entity_id),
	FOREIGN KEY (destination_id) references entity(entity_id) 
);

CREATE TABLE experience (
	experience_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	source_id INT NOT NULL,
	destination_id INT NOT NULL,
	experience_value double unsigned CHECK (i_value >= 0),
	prev_value double unsigned CHECK (i_value >= 0),
	experience_timestamp TIMESTAMP,
	
	FOREIGN KEY (source_id) references entity(entity_id),
	FOREIGN KEY (destination_id) references entity(entity_id) 
);

CREATE TABLE reputation (
	reputation_id INT NOT NULL PRIMARY KEY,
	reputation_value double unsigned CHECK (i_value >= 0),
	reputation_timestamp TIMESTAMP,
	
	FOREIGN KEY (reputation_id) references entity(entity_id) 
);