# betterbdd
[![Build Status](https://travis-ci.org/s0phia/BetterBDD.svg?branch=master)](https://travis-ci.org/s0phia/BetterBDD)


Usage
-----------

Run BetterBDD via the command line by exectuting the following command:
 
    java -jar betterbdd.jar --path <feature file/directory path> --rules <rule confg file> 
    
 ## Available BDD rules

| Name                         | Description                                                                        |   
|------------------------------|------------------------------------------------------------------------------------|
| bad_feature_name             | Prevents a feature definition with a missing or meaningless name.                  |
| bad_scenario_name            | Prevents a scenario definition with a missing or meaningless name.                 |
| bad_step_sequence            | Prevents a scenario definition having an incorrect sequence of scenario steps    	|
| duplicate_scenario_name      | Prevents more than one scenario in a collection of feature files   having the same |   
| duplicate_feature_name       | Prevents more than one feature in a collection of feature files having the same  	|   
| too_many_scenario_steps      | Prevents a scenario definition with an excessive number of steps                   |  
| technical_language           | Prevents the use of technical   language in a scenario definition                  |  
| missing_action_step          | Prevents a scenario definition missing a 'When' action step                      	|  
| missing_verification_step    | Prevents a scenario definition missing a 'Then' action step                      	|   
| named_third_person_narrative | Prevents scenarios not written using a named third-person narrative                |    
| empty_feature_file           | Prevents empty feature files                                                       |  
| missing_scenario_steps       | Prevents a scenario definition without any steps                                   |   