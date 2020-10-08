#!/usr/bin/env bash

export GITHUB_USER=gcloud-repo
export GITHUB_REPO=github.com/centrale-canine/gcp-migration.git
export LOCAL_REPO=gcp-migration
export myConfigFile=./terraform/services-vars.tf

echo "INFO- Clone git project: ................... ${GITHUB_REPO}"

cd /workspace
git clone -b ${GCP_ENV} git@github.com:centrale-canine/gcp-migration

echo "INFO- Update docker image tag in file: ..... ${myConfigFile}"
echo "INFO- New docker image tag: ................ ${BUILD_NAME}"
#echo "INFO- pwd: ................................. $(pwd)"
#echo "INFO- fs: .................................. $(ls -la)"

cd ${LOCAL_REPO}

myTagRoot=`echo ${BUILD_NAME} | sed  's/^\([a-z-]*\)[0-9-]*$/\1/g'`
echo "INFO- myTagRoot: .................................. ${myTagRoot}"

echo "INFO- Original myConfigFile: .................................. $(cat ./terraform/services-vars.tf)"
sed -i "s/${myTagRoot}[0-9-]*/${SHORT_SHA}/" ${myConfigFile} 2>&1
echo "INFO- Final myConfigFile: .................................. $(cat ./terraform/services-vars.tf)"

echo "INFO- Push file: ......... ${myConfigFile}"
echo "INFO- ... to branch: ..... ${GCP_ENV}"

git config user.email "anthony.denecheau@centrale-canine.fr"
git config user.name "${GITHUB_USER}"
git add .
git commit -m":rocket: :wrench: :arrow_up: changed application version from GCP BUILD" .
git push
