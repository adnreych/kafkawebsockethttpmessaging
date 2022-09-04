-- springbootdb.circular_message definition

CREATE TABLE `circular_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_timestamp` datetime(6) DEFAULT NULL,
  `mc1_timestamp` datetime(6) DEFAULT NULL,
  `mc2_timestamp` datetime(6) DEFAULT NULL,
  `mc3_timestamp` datetime(6) DEFAULT NULL,
  `session_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;