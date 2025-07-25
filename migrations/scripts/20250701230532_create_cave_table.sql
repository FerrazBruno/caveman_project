-- // create_cave_table
-- Migration SQL that makes the change goes here.
CREATE SCHEMA prehistoric;

CREATE FUNCTION prehistoric.set_current_timestamp_update_at()
RETURNS TRIGGER AS $$
DECLARE
  _new record;
BEGIN
  _new := NEW;
  _NEW."update_at" = NOW();
  RETURN _new;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE prehistoric.cave (
    id uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    create_at timestamptz NOT NULL DEFAULT now(),
    update_at timestamptz NOT NULL DEFAULT now(),
    description text
);

CREATE TRIGGER set_prehistoric_cave_update_at
BEFORE UPDATE ON prehistoric.cave
FOR EACH ROW
EXECUTE PROCEDURE prehistoric.set_current_timestamp_update_at();

-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE prehistoric.cave;

DROP FUNCTION prehistoric.set_current_timestamp_update_at;

DROP SCHEMA prehistoric;

