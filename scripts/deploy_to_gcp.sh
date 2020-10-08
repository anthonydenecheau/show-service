#!/usr/bin/env bash

export GITHUB_USER=gcloud-repo
export GITHUB_REPO=github.com/centrale-canine/gcp-migration.git
export LOCAL_REPO=gcp-migration
export myConfigFile=./terraform/services-vars.tf
export myTagRoot=scc-show-service

echo "INFO- Clone git project: ................... ${GITHUB_REPO}"

cd /workspace
git clone -b ${GCP_ENV} git@github.com:centrale-canine/gcp-migration

echo "INFO- Update docker image tag in file: ..... ${myConfigFile}"
echo "INFO- New docker image tag: ................ ${TAG_NAME}"

cd ${LOCAL_REPO}
sed -i "s/${myTagRoot}[a-z0-9:-]*/${TAG_NAME}/" ${myConfigFile} 2>&1

echo "INFO- Push file: ......... ${myConfigFile}"
echo "INFO- ... to branch: ..... ${GCP_ENV}"

#git config user.email "anthony.denecheau@centrale-canine.fr"
#git config user.name "${GITHUB_USER}"
#git add .
#git commit -m":rocket: :wrench: :arrow_up: changed application version from GCP BUILD" .
#git push
