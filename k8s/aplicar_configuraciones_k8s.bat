@echo off
echo Aplicando configuraciones de Kubernetes...

kubectl apply -f postgres-deployment.yaml
kubectl apply -f backend-deployment.yaml
kubectl apply -f frontend-deployment.yaml
kubectl apply -f ingress.yaml

echo configuraciones aplicadas correctamente.
pause
