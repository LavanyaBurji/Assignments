-- Create a table with a JSONB column
CREATE TABLE json_data (
    id SERIAL PRIMARY KEY,
    data JSONB
);

-- Insert JSON data
INSERT INTO json_data (data) VALUES 
('{"name": "Alice", "age": 25, "city": "New York"}'),
('{"name": "Bob", "age": 30, "city": "San Francisco"}');

-- Retrieve all JSON data
SELECT * FROM json_data;

-- Retrieve specific JSON fields
SELECT data->>'name' AS name, data->>'age' AS age FROM json_data;

-- Update JSON data (change Alice's age to 28)
UPDATE json_data 
SET data = jsonb_set(data, '{age}', '28') 
WHERE data->>'name' = 'Alice';

-- Filter data where city is New York
SELECT * FROM json_data WHERE data->>'city' = 'New York';
