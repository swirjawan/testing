#!/bin/bash

echo Deploying core jobs...

FLINK_ROOT=/opt/flink
FLINK_CONF_DIR=$FLINK_ROOT/conf

DS_NAME=datalake
JM_HOST=jobmanager:8081
DEV_CONFIG=$FLINK_ROOT/conf/config_dev.properties
MSG_CONFIG=$FLINK_ROOT/conf/message_config.properties

CORE_JAR=$FLINK_ROOT/core_jobs/flink-bootstrap-0.0.8-SNAPSHOT.jar
USER_JAR=$FLINK_ROOT/core_jobs/user-services-0.0.1-SNAPSHOT.jar
CUSTOMER_JAR=$FLINK_ROOT/core_jobs/customer-services-0.0.1-SNAPSHOT.jar
CONTENT_JAR=$FLINK_ROOT/core_jobs/content-services-0.0.1-SNAPSHOT.jar

echo Deploying BootstrapJob in taskmgr1  ...

export jobPrefix=TM1

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c com.prudential.platform.services.BootstrapJob $CORE_JAR

echo Deploying BootstrapJob in taskmgr2 ...

export jobPrefix=TM2

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c com.prudential.platform.services.BootstrapJob $CORE_JAR

unset jobPrefix

echo Deploying EntityReadJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.platform.services.data.EntityReadJob $CORE_JAR $DS_NAME

echo Deploying EntityWriteJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.platform.services.data.EntityWriteJob $CORE_JAR $DS_NAME

#echo Deploying MessageSenderJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.platform.services.transport.message.MessageSenderJob $CORE_JAR  --config $MSG_CONFIG

#echo Deploying RefreshTokenJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.platform.services.auth.RefreshTokenJob $CORE_JAR

#echo Deploying UserRegistrationJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.user.services.UserRegistrationJob $USER_JAR --config $DEV_CONFIG

#echo Deploying UserLoginJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.user.services.UserLoginJob $USER_JAR 

#echo Deploying User UpdateUserJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.user.services.UpdateUserJob $USER_JAR

#echo Deploying User ResetPasswordJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.user.services.ResetPasswordJob $USER_JAR  --config $DEV_CONFIG

#echo Deploying User GetCustomerJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.customer.services.GetCustomerJob  $CUSTOMER_JAR  --config $DEV_CONFIG

#echo Deploying User UpdateCustomerJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.customer.services.UpdateCustomerJob  $CUSTOMER_JAR

#echo Deploying User DeleteCustomerDetailsJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.customer.services.DeleteCustomerDetailsJob  $CUSTOMER_JAR

#echo Deploying User GetContentJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.content.services.GetContentJob $CONTENT_JAR

#echo Deploying User GetResourceJob...

#gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.prutopia.content.services.GetResourceJob $CONTENT_JAR
