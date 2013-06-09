create function tree_level(varchar) returns integer as '
declare
        inkey     alias for $1;
        cnt       integer default 0;
begin
        for i in 1..length(inkey) LOOP
            if substr(inkey,i,1) = ''/'' then
               cnt := cnt + 1;
            end if;
        end LOOP;

        return cnt;

end; language 'plpgsql';


/*Caso a linguagem nã seja reconhecida:
	CREATE FUNCTION plpgsql_call_handler() RETURNS language_handler AS
    '$libdir/plpgsql' LANGUAGE C;

	CREATE FUNCTION plpgsql_validator(oid) RETURNS void AS
	    '$libdir/plpgsql' LANGUAGE C;
	
	CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql
	    HANDLER plpgsql_call_handler
	    VALIDATOR plpgsql_validator;
*/