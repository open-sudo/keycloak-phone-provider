oc create -f buildconfig.yaml
oc create imagestream nginx-hello-world
oc start-build nginx-hello-world --from-dir=./ --follow
oc create -f deployment.yaml
oc create -f clusterip.yaml
oc create -f route.yaml
