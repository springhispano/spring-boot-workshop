
--pwd:secreto
--crear usuario
createuser -d sboot -P

--crear schema
createdb -E utf8 -O sboot -W -U sboot taller