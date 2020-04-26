# Server 

## Getting Started

Go here: https://console.aws.amazon.com/eks/home?region=us-east-1#/clusters
1. Install AWS CLI
```shell script
$ brew install awscli
```

- Configure AWS CLI. Replace the Value with your access Key. 

    __Note:__ Find your IAM key [here](https://console.aws.amazon.com/iam/home#/security_credentials). Then, click on "Access keys (access key ID and secret access key)" and create and copy the access and secret to the below:
    
```shell script
$ aws configure
AWS Access Key ID [None]: AKIAIOSFODNN7EXAMPLE
AWS Secret Access Key [None]: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
Default region name [None]: us-west-2
Default output format [None]: json
```

```
docker build -f Dockerfile -t csearch .
docker tag csearch:latest 103806448707.dkr.ecr.us-east-1.amazonaws.com/csearch:latest
docker push 103806448707.dkr.ecr.us-east-1.amazonaws.com/csearch:latest
docker pull 103806448707.dkr.ecr.us-east-1.amazonaws.com/csearch:latest
docker run -p 80:8080 103806448707.dkr.ecr.us-east-1.amazonaws.com/csearch:latest
```


https://us-east-1.console.aws.amazon.com/ecr/repositories/csearch/?region=us-east-1

```

## Docker
Run in your local:
```shell script
$ mvn 
$ docker build -f Dockerfile -t cuongvqnguyen/csearch .
$ docker push cuongvqnguyen/csearch
$ docker run -p 8080:8080 -t cuongvqnguyen/csearch
```

```shell script
$ ssh -i "ec2-key-pair.pem" ec2-user@ec2-3-94-114-117.compute-1.amazonaws.com
$ sudo yum update -y
$ udo yum install docker
$ sudo service docker start
$ sudo docker pull cuongvqnguyen/csearch
$ sudo docker run -p 80:8080 -t cuongvqnguyen/csearch
```
Now open your browser
