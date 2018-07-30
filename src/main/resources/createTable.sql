-- Remove old tables
DROP TABLE IF EXISTS Person;

-- Create table
CREATE TABLE Person (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(150),
  last_name VARCHAR(150),
  birthday DATE,
  email VARCHAR(150),
  phone VARCHAR(150),
  job VARCHAR(150)
);

-- Insert data
INSERT INTO Person VALUES (id, 'Giulietta', 'Capuleti', '2002-01-27', 'giulietta@yahoo.it', '255-145-1605', 'Unemployed');
INSERT INTO Person VALUES (id, 'Emilia', 'Clarke', '1986-10-23', 'daenerys_targaryen@yahoo.com', '565-137-5896', 'Actress');
INSERT INTO Person VALUES (id, 'Lena', 'Headey', '1986-01-27', 'cersei_lannister@hollywood.com', '336-150-5147', 'Actress');

SELECT * FROM Person;