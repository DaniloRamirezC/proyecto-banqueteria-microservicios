INSERT INTO categoria (nombre_categoria)
VALUES ('Banquetería')
ON CONFLICT (nombre_categoria) DO NOTHING;

INSERT INTO categoria (nombre_categoria)
VALUES ('Pastelería')
ON CONFLICT (nombre_categoria) DO NOTHING;

INSERT INTO menu (nombre_menu, detalle, precio, id_categoria)
SELECT v.nombre, v.detalle, v.precio, c.id_categoria
FROM (VALUES
    ('Torta de chocolate', 'Bizcocho de chocolate, relleno cremoso y cobertura de ganache.', 42000.00),
    ('Torta de frutilla', 'Bizcocho de vainilla, crema y frutillas frescas.', 45000.00),
    ('Torta Red Velvet', 'Bizcocho red velvet con frosting suave de queso crema.', 48000.00),
    ('Torta manjar nuez', 'Bizcocho suave con manjar y nueces.', 44000.00),
    ('Torta de limón merengue', 'Base suave, crema de limón y merengue dorado.', 38000.00),
    ('Torta tres leches', 'Bizcocho húmedo tres leches con crema y frutas.', 43000.00)
) AS v(nombre, detalle, precio)
CROSS JOIN categoria c
WHERE c.nombre_categoria = 'Pastelería'
AND NOT EXISTS (
    SELECT 1
    FROM menu m
    WHERE m.nombre_menu = v.nombre
      AND m.id_categoria = c.id_categoria
);
