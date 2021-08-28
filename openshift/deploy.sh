#oc create configmap keycloak-config --from-file=configmap.yaml 
oc create -f buildconfig.yaml
oc create imagestream keycloak-appui
oc start-build keycloak-appui --from-dir=./ --follow
oc create -f deployment.yaml
oc create -f clusterip.yaml
oc create -f route.yaml
