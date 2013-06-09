CREATE VIEW VW_DIRETORIO AS
SELECT diretorio_codigo, nom_diretorio, 
       diretorio_pai_codigo, str_sort_key,
       tree_level(str_sort_key) as level 
FROM diretorio
ORDER BY str_sort_key 