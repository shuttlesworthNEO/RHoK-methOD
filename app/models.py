# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from Registration.models import UserModel
# Create your models here.

class QueryModel(models.Model):
    phone = models.CharField(max_length=20)
    key = models.CharField(max_length=10)
    query = models.CharField(max_length=10000)

class TakeupModel(models.Model):
    user = models.ForeignKey(UserModel)
    query = models.ForeignKey(QueryModel)
    takenup = models.BooleanField(default=False)
    resolved = models.BooleanField(default=False)

