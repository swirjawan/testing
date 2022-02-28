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

USER_JAR=$FLINK_ROOT/core_jobs/pmn-user-management-1.0.0.jar
CASE_JAR=$FLINK_ROOT/core_jobs/pmn-case-management-1.0.0.jar
ADM_JAR=$FLINK_ROOT/core_jobs/pmn-admission-1.0.0.jar

echo Deploying BootstrapJob in taskmgr1  ...
export jobPrefix=TM1
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c com.prudential.platform.services.BootstrapJob $CORE_JAR

echo Deploying BootstrapJob in taskmgr2 ...
eexport jobPrefix=TM2
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c com.prudential.platform.services.BootstrapJob $CORE_JAR

unset jobPrefix

echo Deploying EntityReadJob...
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.platform.services.data.EntityReadJob $CORE_JAR $DS_NAME

echo Deploying EntityWriteJob...
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c com.prudential.platform.services.data.EntityWriteJob $CORE_JAR $DS_NAME

echo Deploying UserManagement
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c id.co.prudential.pmn.usermgmt.UserManagementJob $USER_JAR

echo Deploying CaseManagement
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c id.co.prudential.pmn.casemgmt.jobs.PmnCaseJob $CASE_JAR

echo Deploying Admission
gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 1 -d -c id.co.prudential.pmn.policy.PolicyInquiryJob $ADM_JAR

#echo Deploying Document
