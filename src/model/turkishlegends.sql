-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 08 mai 2018 à 20:33
-- Version du serveur :  10.1.29-MariaDB
-- Version de PHP :  7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `turkishlegends`
--

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `customer`
--

INSERT INTO `customer` (`id`, `phone`, `address`) VALUES
(2, '0659388869', '34 jonquille street'),
(3, '0123456789', 'who cares'),
(6, '06', 'evooo lol'),
(7, '06', 'evo !');

-- --------------------------------------------------------

--
-- Structure de la table `customer_created_recipe`
--

CREATE TABLE `customer_created_recipe` (
  `customer_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `customer_created_recipe`
--

INSERT INTO `customer_created_recipe` (`customer_id`, `recipe_id`) VALUES
(2, 28),
(3, 25),
(3, 26);

-- --------------------------------------------------------

--
-- Structure de la table `demand`
--

CREATE TABLE `demand` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `kebab_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 waiting, 1 being prepared, 2 ready, 3 being delivered, 4 completed'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demand`
--

INSERT INTO `demand` (`id`, `user_id`, `kebab_id`, `date`, `status`) VALUES
(11, 3, 21, '2018-05-08', 4);

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

CREATE TABLE `ingredient` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `image_path` varchar(50) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0 bread, 1 meat, 2 vegetable, 3 sauce'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ingredient`
--

INSERT INTO `ingredient` (`id`, `name`, `price`, `image_path`, `type`) VALUES
(1, 'Pita', 0, NULL, 0),
(2, 'Dürüm', 0, NULL, 0),
(3, 'Lamb', 0, NULL, 1),
(4, 'Sheep', 0, NULL, 1),
(5, 'Veal', 0, NULL, 1),
(6, 'Turkey', 0, NULL, 1),
(7, 'Beef', 0, NULL, 1),
(8, 'Veal - Turkey', 0, NULL, 1),
(9, 'Onion', 0, NULL, 2),
(10, 'Salad', 0, NULL, 2),
(11, 'Tomato', 0, NULL, 2),
(12, 'Red pepper', 0, NULL, 2),
(13, 'Carrot', 0, NULL, 2),
(14, 'Green pepper', 0, NULL, 2),
(16, 'Curry', 0, NULL, 3),
(17, 'Pepper', 0, NULL, 3),
(18, 'Mayonnaise', 0, NULL, 3),
(19, 'Ketchup', 0, NULL, 3),
(20, 'White', 0, NULL, 3),
(21, 'Samurai', 0, NULL, 3),
(22, 'Bearnaise', 0, NULL, 3),
(23, 'Harissa', 0, NULL, 3),
(24, 'Barbecue', 0, NULL, 3),
(25, 'Honey', 0, NULL, 3),
(27, 'No meat', 0, NULL, 1),
(28, 'No vegetable', 0, NULL, 2),
(29, 'No sauce', 0, NULL, 3);

-- --------------------------------------------------------

--
-- Structure de la table `kebab`
--

CREATE TABLE `kebab` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `kebab`
--

INSERT INTO `kebab` (`id`, `name`, `recipe_id`) VALUES
(21, 'Classic', 25),
(22, 'Mayo', 26),
(24, 'My legendary kebab', 28);

-- --------------------------------------------------------

--
-- Structure de la table `recipe`
--

CREATE TABLE `recipe` (
  `id` int(11) NOT NULL,
  `bread_id` int(11) NOT NULL DEFAULT '1',
  `meat_id` int(11) DEFAULT '27',
  `sauce_id` int(11) DEFAULT '29',
  `vegetable1_id` int(11) DEFAULT '28',
  `vegetable2_id` int(11) DEFAULT '28',
  `vegetable3_id` int(11) DEFAULT '28'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `recipe`
--

INSERT INTO `recipe` (`id`, `bread_id`, `meat_id`, `sauce_id`, `vegetable1_id`, `vegetable2_id`, `vegetable3_id`) VALUES
(25, 1, 3, 21, 9, 10, 11),
(26, 1, 8, 18, 9, 10, 11),
(28, 1, 7, 22, 9, 10, 11);

-- --------------------------------------------------------

--
-- Structure de la table `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `salary` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `staff`
--

INSERT INTO `staff` (`id`, `salary`) VALUES
(1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `firstName`, `lastName`, `email`, `password`) VALUES
(1, 'Dorian', 'ANTOINET', 'dorian.antoinet@efrei.net', 'dodo'),
(2, 'Sarah', 'EDELSTEIN', 'sarah@yahoo.com', 'eden'),
(3, 'Rapid', 'Test', 'email', 'password'),
(6, 'Victor', 'Delenclos', 'vd@efrei.net', 'victor'),
(7, 'Aymeric', 'DAVIAS', 'test@test.fr', 'test');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`) USING BTREE;

--
-- Index pour la table `customer_created_recipe`
--
ALTER TABLE `customer_created_recipe`
  ADD PRIMARY KEY (`customer_id`,`recipe_id`) USING BTREE,
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Index pour la table `demand`
--
ALTER TABLE `demand`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `kebab`
--
ALTER TABLE `kebab`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Index pour la table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bread_id` (`bread_id`),
  ADD KEY `meat_id` (`meat_id`),
  ADD KEY `sauce_id` (`sauce_id`),
  ADD KEY `vegetable1_id` (`vegetable1_id`),
  ADD KEY `vegetable2_id` (`vegetable2_id`),
  ADD KEY `vegetable3_id` (`vegetable3_id`);

--
-- Index pour la table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`) USING BTREE;

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `demand`
--
ALTER TABLE `demand`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `kebab`
--
ALTER TABLE `kebab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `customer_created_recipe`
--
ALTER TABLE `customer_created_recipe`
  ADD CONSTRAINT `customer_created_recipe_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `customer_created_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`),
  ADD CONSTRAINT `customer_created_recipe_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `customer_created_recipe_ibfk_4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `customer_created_recipe_ibfk_5` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `demand`
--
ALTER TABLE `demand`
  ADD CONSTRAINT `demand_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `kebab`
--
ALTER TABLE `kebab`
  ADD CONSTRAINT `kebab_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`);

--
-- Contraintes pour la table `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`bread_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `recipe_ibfk_2` FOREIGN KEY (`meat_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `recipe_ibfk_3` FOREIGN KEY (`sauce_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `recipe_ibfk_4` FOREIGN KEY (`vegetable1_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `recipe_ibfk_5` FOREIGN KEY (`vegetable2_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `recipe_ibfk_6` FOREIGN KEY (`vegetable3_id`) REFERENCES `ingredient` (`id`);

--
-- Contraintes pour la table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
