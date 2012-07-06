CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `operator`
AS select
   `d`.`ID` AS `id`,
   `d`.`EXTERNAL_ID` AS `external_id`,
   `d`.`NAME` AS `name`,
   `d`.`MAX_CHARGE` AS `max_charge`,
   `d`.`MAX_VOLUME` AS `max_volume`,
   `d`.`MAX_DURATION` AS `max_duration`
from (`studio1`.`operator` `d`);