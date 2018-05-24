-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 24 mai 2018 à 18:00
-- Version du serveur :  10.1.32-MariaDB
-- Version de PHP :  7.2.5

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
(20, '0123456789', 'Evo');

-- --------------------------------------------------------

--
-- Structure de la table `customer_created_recipe`
--

CREATE TABLE `customer_created_recipe` (
  `customer_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(37, 20, NULL, '2018-05-24', 0);

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
(1, 'Pita', 1.3, NULL, 0),
(2, 'Dürüm', 1.5, NULL, 0),
(3, 'Lamb', 1, NULL, 1),
(4, 'Sheep', 1.3, NULL, 1),
(5, 'Veal', 1.5, NULL, 1),
(6, 'Turkey', 0.8, NULL, 1),
(7, 'Beef', 2, NULL, 1),
(8, 'Veal - Turkey', 1.2, NULL, 1),
(9, 'Onion', 0.5, NULL, 2),
(10, 'Salad', 0.7, NULL, 2),
(11, 'Tomato', 0.8, NULL, 2),
(12, 'Red pepper', 1, NULL, 2),
(13, 'Carrot', 0.5, NULL, 2),
(14, 'Green pepper', 1, NULL, 2),
(16, 'Curry', 1.6, NULL, 3),
(17, 'Pepper', 0.5, NULL, 3),
(18, 'Mayonnaise', 0.5, NULL, 3),
(19, 'Ketchup', 0.5, NULL, 3),
(20, 'White', 0.8, NULL, 3),
(21, 'Samurai', 1, NULL, 3),
(22, 'Bearnaise', 1.2, NULL, 3),
(23, 'Harissa', 1.2, NULL, 3),
(24, 'Barbecue', 1, NULL, 3),
(25, 'Honey', 3, NULL, 3),
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
(24, 'My legendary kebab', 28),
(38, 'KebabTest', 42),
(39, 'dzdz', 43),
(40, 'Kebib', 44),
(42, 'KebabMouloud', 46),
(43, 'KebabTest', 47);

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
(28, 1, 7, 22, 9, 10, 11),
(42, 2, 5, 17, 10, 11, 10),
(43, 2, 4, 19, 9, 11, 11),
(44, 2, 5, 21, 10, 10, 13),
(46, 2, 6, 29, 10, 10, 28),
(47, 2, 4, 29, 10, 28, 28);

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
(17, 1);

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
(17, 'admin', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
(20, 'customer', 'customer', 'custom@er', '91ec1f9324753048c0096d036a694f86');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT pour la table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `kebab`
--
ALTER TABLE `kebab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
