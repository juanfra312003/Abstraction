USE abstraction;

-- Insercion productos
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('1', 'W91', '8500', '200', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('2', 'W86', '9600', '150', 'Sueco dama 36/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('3', 'W60', '9000', '300', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('4', 'W72', '9600', '200', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('5', 'W93', '9800', '200', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('6', '1907', '9200', '500', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('7', '028', '10900', '200', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('8', '825', '9000', '200', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('9', '666', '9000', '200', 'Sueco dama con camara de aire 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('10', 'W37', '11500', '420', 'Sueco dama 35/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('11', '018', '12800', '200', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('12', 'W72', '9600', '200', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('13', 'M75', '10500', '100', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('14', 'W93', '9800', '300', 'Sueco hombre 36/40', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('15', '001', '12800', '200', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('16', '1915', '9500', '200', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('17', '028', '11500', '500', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('18', '007', '12800', '200', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('19', '022H', '9000', '100', 'Sueco hombre 39/44', '0');
INSERT INTO producto(referencia, nombre, precio, existencias, descripcion, archivado)
VALUES('20', 'M91', '9500', '350', 'Sueco hombre 39/44', '0');

-- Insercion Cotizaciones
INSERT INTO cotizacion(numero, nombre, fecha, precioTotal, nombreCliente, archivado)
VALUES ('1', 'Arequipe', STR_TO_DATE('08/10/2022','%d/%m/%Y'), '666000', 'Carlos' , '0');
INSERT INTO cotizacion(nombre, fecha, precioTotal, nombreCliente, archivado)
VALUES ('Diane & Geordi', STR_TO_DATE('20/09/2022','%d/%m/%Y'), '1570000', 'Diana' , '0');
INSERT INTO cotizacion(nombre, fecha, precioTotal, nombreCliente, archivado)
VALUES ('Todo a mil Suba', STR_TO_DATE('05/11/2022','%d/%m/%Y'), '332000', 'Felipe' , '0');
INSERT INTO cotizacion(nombre, fecha, precioTotal, nombreCliente, archivado)
VALUES ('Bucaramanga', STR_TO_DATE('10/08/2022','%d/%m/%Y'), '332000', 'Pablo' , '0');
INSERT INTO cotizacion(nombre, fecha, precioTotal, nombreCliente, archivado)
VALUES ('Baraton', STR_TO_DATE('15/10/2022','%d/%m/%Y'), '332000', 'Juana' , '0');

-- Insercion cotizacionProducto
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','1','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','2','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','3','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','4','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','5','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','6','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('1','7','10');

INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','9','100');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','10','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','11','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','12','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','13','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','14','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('2','15','10');

INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('3','7','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('3','15','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('3','20','10');

INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('4','7','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('4','15','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('4','20','10');

INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('5','7','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('5','15','10');
INSERT INTO cotizacionproducto(cotizacion_numero, producto_referencia, cantidad)
VALUES ('5','20','10');
