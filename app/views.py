# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import JsonResponse
from Registration.models import UserModel
import json
from django.views.decorators.csrf import csrf_exempt
from models import KeyModel, QueryModel, TakeupModel
# Create your views here.

@csrf_exempt
def KeyView(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode(encoding='UTF-8'))
        key = data['key']
        obj = KeyModel(key=key)
        obj.save()
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def QueryView(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode(encoding='UTF-8'))
        key = data['key']
        query = data['query']
        key_object = KeyModel.objects.filter(key=key).first()
        query_object = QueryModel(key=key_object, query=query)
        query_object.save()
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def TakeupView(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode(encoding='UTF-8'))
        user_key = data['user_key']
        query_key = data['query_key']
        user_obj = UserModel.objects.filter(key=user_key).first()
        query_obj = QueryModel.objects.filter(key=query_key).first()
        takeup_obj = TakeupModel(user=user_obj, query=query_obj, takenup=True)
        takeup_obj.save()
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def ResolvedView(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode(encoding='UTF-8'))
        key = data['key']
        query_obj = QueryModel.objects.filter(key=key)
        TakeupModel.objects.filter(query=query_obj.first()).delete()
        if data['resolved'] == 1:
            query_obj.delete()
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def FeedView(request):
    if request.method == 'POST':
        query_obj = QueryModel.objects.all()
        query = []
        for x in query_obj:
            temp = {
                'key' : x.key,
                'query' : x.query,
            }
            query.append(temp)
        resp = {
            'code' : 200,
            'data' : query,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

