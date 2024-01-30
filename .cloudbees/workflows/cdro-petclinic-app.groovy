application 'petclinic-app', {
  description = ''
  applicationType = 'microservice'
  projectName = 'MyProject'

  microservice 'petclinic-app', {
    description = ''
    applicationName = 'petclinic-app'
    definitionSource = 'yaml_content'
    definitionSourceParameter = [
      'sourceType': 'content',
      'yamlContent': '''apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: petclinic-py
  name: petclinic-py
spec:
  replicas: $[/myEnvironment/scale]
  selector:
    matchLabels:
      app: petclinic-py
  template:
    metadata:
      labels:
        app: petclinic-py
    spec:
      containers:
      - image: "rbroker/petclinic-app:$[appVersion]"
        name: petclinic-py
        ports:
          - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: petclinic-py-svc
spec:
  selector:
    app: petclinic-py
  ports:
  - protocol: "TCP"
    port: 8080
    targetPort: 8080
  type: ClusterIP

---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: petclinic-py-ingress
  labels:
    app: petclinic-py
  annotations:
    nginx.ingress.kubernetes.io/ssl-redirect: "false"

spec:
  rules:
  - host: $[/myEnvironment/PETCLINIC_HOST]
    http:
      paths:
      - backend:
          service:
            name: petclinic-py-svc
            port:
              number: 8080
        path: /
        pathType: ImplementationSpecific''',
    ]
    definitionType = 'yaml'
    deployParameter = [
      'additionalOptions': '',
      'relativePath': '',
    ]
    process 'Deploy Microservice Process', {
      description = 'System generated process for microservice deployment'
      applicationName = null
      exclusiveEnvironment = null
      microserviceName = 'petclinic-app'
      processType = 'DEPLOY'
      smartUndeployEnabled = null
      timeLimit = null
      timeLimitUnits = 'minutes'
      workingDirectory = null
      workspaceName = null

      processStep 'Retrieve Artifact', {
        description = 'System generated step to retrieve microservice definition artifact'
        actionLabelText = null
        afterLastRetry = null
        allowSkip = null
        alwaysRun = '0'
        applicationTierName = null
        componentRollback = null
        dependencyJoinType = null
        disableFailure = null
        emailConfigName = null
        errorHandling = 'abortJob'
        instruction = null
        notificationEnabled = null
        notificationTemplate = null
        processStepType = 'plugin'
        retryCount = null
        retryInterval = null
        retryType = null
        rollbackSnapshot = null
        rollbackType = null
        rollbackUndeployProcess = null
        skipRollbackIfUndeployFails = null
        smartRollback = null
        subcomponent = null
        subcomponentApplicationName = null
        subcomponentProcess = null
        submicroservice = null
        submicroserviceProcess = null
        subprocedure = 'Source Provider'
        subproject = '/plugins/EC-Kubectl/project'
        timeLimit = null
        timeLimitUnits = null
        useUtilityResource = '0'
        utilityResourceName = null
        workingDirectory = null
        workspaceName = null
      }

      processStep 'Deploy Microservice', {
        description = 'System generated step to deploy microservice'
        actionLabelText = null
        afterLastRetry = null
        allowSkip = null
        alwaysRun = '0'
        applicationTierName = null
        componentRollback = null
        dependencyJoinType = null
        disableFailure = null
        emailConfigName = null
        errorHandling = 'abortJob'
        instruction = null
        notificationEnabled = null
        notificationTemplate = null
        processStepType = 'plugin'
        retryCount = null
        retryInterval = null
        retryType = null
        rollbackSnapshot = null
        rollbackType = null
        rollbackUndeployProcess = null
        skipRollbackIfUndeployFails = null
        smartRollback = null
        subcomponent = null
        subcomponentApplicationName = null
        subcomponentProcess = null
        submicroservice = null
        submicroserviceProcess = null
        subprocedure = 'Deploy Service'
        subproject = '/plugins/EC-Kubectl/project'
        timeLimit = null
        timeLimitUnits = null
        useUtilityResource = '0'
        utilityResourceName = null
        workingDirectory = null
        workspaceName = null
      }

      processDependency 'Retrieve Artifact', targetProcessStepName: 'Deploy Microservice', {
        branchCondition = null
        branchConditionName = null
        branchConditionType = null
        branchType = 'ALWAYS'
      }
    }
  }

  process 'Deploy Application', {
    description = 'System generated process for microservice application'
    applicationName = 'petclinic-app'
    exclusiveEnvironment = null
    microserviceName = null
    processType = 'DEPLOY'
    smartUndeployEnabled = null
    timeLimit = null
    timeLimitUnits = 'minutes'
    workingDirectory = null
    workspaceName = null

    formalParameter 'appVersion', defaultValue: '1.0.0', {
      expansionDeferred = '0'
      label = null
      orderIndex = '1'
      required = '1'
      type = 'entry'
    }

    formalParameter 'ec_petclinic-app-run', defaultValue: '1', {
      checkedValue = null
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'checkbox'
      uncheckedValue = null
    }

    formalParameter 'ec_rolloutApprovers', defaultValue: '', {
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'assigneeList'
    }

    formalParameter 'ec_rolloutNotificationEnabled', defaultValue: '0', {
      checkedValue = null
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'checkbox'
      uncheckedValue = null
    }

    processStep 'petclinic-app', {
      description = 'System generated step to invoke microservice process'
      actionLabelText = null
      afterLastRetry = null
      allowSkip = null
      alwaysRun = '0'
      applicationTierName = null
      componentRollback = null
      dependencyJoinType = null
      disableFailure = null
      emailConfigName = null
      errorHandling = 'abortJob'
      instruction = null
      notificationEnabled = null
      notificationTemplate = null
      processStepType = 'process'
      retryCount = null
      retryInterval = null
      retryType = null
      rollbackSnapshot = null
      rollbackType = null
      rollbackUndeployProcess = null
      skipRollbackIfUndeployFails = null
      smartRollback = null
      subcomponent = null
      subcomponentApplicationName = null
      subcomponentProcess = null
      submicroservice = 'petclinic-app'
      submicroserviceProcess = 'Deploy Microservice Process'
      subprocedure = null
      subproject = null
      timeLimit = null
      timeLimitUnits = null
      useUtilityResource = '1'
      utilityResourceName = null
      workingDirectory = null
      workspaceName = null

      // Custom properties

      property 'ec_deploy', {

        // Custom properties
        ec_notifierStatus = '0'
      }
    }

    processStep 'petclinic-Application', {
      description = 'System generated step to invoke microservice process'
      actionLabelText = null
      afterLastRetry = null
      allowSkip = null
      alwaysRun = '0'
      applicationTierName = null
      componentRollback = null
      dependencyJoinType = null
      disableFailure = null
      emailConfigName = null
      errorHandling = 'abortJob'
      instruction = null
      notificationEnabled = null
      notificationTemplate = null
      processStepType = 'process'
      retryCount = null
      retryInterval = null
      retryType = null
      rollbackSnapshot = null
      rollbackType = null
      rollbackUndeployProcess = null
      skipRollbackIfUndeployFails = null
      smartRollback = null
      subcomponent = null
      subcomponentApplicationName = null
      subcomponentProcess = null
      submicroservice = 'petclinic-app'
      submicroserviceProcess = 'Deploy Microservice Process'
      subprocedure = null
      subproject = null
      timeLimit = null
      timeLimitUnits = null
      useUtilityResource = '1'
      utilityResourceName = null
      workingDirectory = null
      workspaceName = null

      // Custom properties

      property 'ec_deploy', {

        // Custom properties
        ec_notifierStatus = '0'
      }
    }

    // Custom properties

    property 'ec_deploy', {

      // Custom properties
      ec_notifierStatus = '0'
    }
  }

  tierMap '7457a84a-b6de-11ed-8723-f20c21f2513d', {
    applicationName = 'petclinic-app'
    environmentName = 'test-dev'
    environmentProjectName = 'MyProject'
    microserviceMapping 'fc9c2873-467e-11ee-8cc4-4e7a220a60e8', {
      clusterName = 'Default Cluster - test-dev'
      clusterProjectName = 'MyProject'
      microserviceName = 'petclinic-app'
      tierMapName = '7457a84a-b6de-11ed-8723-f20c21f2513d'
    }
  }

  tierMap '7a5180ef-b6de-11ed-ae7c-f20c21f2513d', {
    applicationName = 'petclinic-app'
    environmentName = 'test-test'
    environmentProjectName = 'MyProject'
    microserviceMapping 'fc9fd1f7-467e-11ee-8cc4-4e7a220a60e8', {
      clusterName = 'Default - test-test'
      clusterProjectName = 'MyProject'
      microserviceName = 'petclinic-app'
      tierMapName = '7a5180ef-b6de-11ed-ae7c-f20c21f2513d'
    }
  }

  // Custom properties

  property 'ec_deploy', {

    // Custom properties
    ec_notifierStatus = '0'
  }
  appVersion = 'latest'

  property 'jobCounter', value: '159', {
    expandable = '1'
    suppressValueTracking = '1'
  }
}