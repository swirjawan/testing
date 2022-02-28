#!/bin/bash

echo Deploying usermgmt jobs...

FLINK_ROOT=/opt/flink
FLINK_CONF_DIR=$FLINK_ROOT/conf

JM_HOST=jobmanager:8081

USERMGMT_JAR=$FLINK_ROOT/core_jobs/pmn-user-management-1.0.0.jar

echo Deploying UserMgmtGeneralJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.GeneralJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtPendingJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.PendingJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtAuditlogJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.AuditlogJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtAuthJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.AuthJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtUserRoleJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.UserRoleJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtUserGroupJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.UserGroupJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtUserDivisionJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.UserDivisionJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtUserJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.UserJob $CORE_JAR $USERMGMT_JAR

echo Deploying UserMgmtHospitalJob...

gosu flink "$FLINK_HOME/bin/flink" run -m $JM_HOST -p 2 -d -c id.co.prudential.pmn.usermgmt.services.HospitalJob $CORE_JAR $USERMGMT_JAR


