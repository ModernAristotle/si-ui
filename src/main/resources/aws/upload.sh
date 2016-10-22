#!/usr/bin/env bash
aws s3 sync . s3://siorg/admin/dist/  --acl public-read  --acl bucket-owner-full-control --metadata Cache-Control=max-age=1296000
