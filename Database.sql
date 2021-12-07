CREATE TABLE `categoria` (
  `id_cat` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cat`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `produto` (
  `id_prod` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `codigo` int NOT NULL,
  `preco` float NOT NULL,
  `fabricante` varchar(50) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `estoque` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_prod`),
  UNIQUE KEY `codigo` (`codigo`),
  KEY `FK_ProdCat` (`id_categoria`),
  CONSTRAINT `FK_ProdCat` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_cat`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `data_nasc` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `venda` (
  `id_venda` int NOT NULL AUTO_INCREMENT,
  `num_venda` int NOT NULL,
  `data_venda` date NOT NULL,
  `id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id_venda`),
  UNIQUE KEY `num_venda` (`num_venda`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `venda_produto` (
  `id_venda` int NOT NULL,
  `id_prod` int NOT NULL,
  `qtd_vend` int DEFAULT NULL,
  `preco_vend` float DEFAULT NULL,
  PRIMARY KEY (`id_venda`,`id_prod`),
  KEY `id_prod` (`id_prod`),
  CONSTRAINT `venda_produto_ibfk_1` FOREIGN KEY (`id_venda`) REFERENCES `venda` (`id_venda`),
  CONSTRAINT `venda_produto_ibfk_2` FOREIGN KEY (`id_prod`) REFERENCES `produto` (`id_prod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;