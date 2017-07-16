# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import JsonResponse
from Registration.models import UserModel
import json
from django.views.decorators.csrf import csrf_exempt
from models import QueryModel, TakeupModel
# Create your views here.

@csrf_exempt
def QueryView(request):
    if request.method == 'POST':
        print request.body
        data = json.loads(request.body.decode(encoding='UTF-8'))
        key = data['key']
        query = data['query']
        phone = data['phone']
        query_object = QueryModel(key=key, query=query, phone=phone)
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
        query_id = data['id']
        user_obj = UserModel.objects.filter(key=user_key).first()
        query_obj = QueryModel.objects.filter(id=query_id).first()
        takeup_obj = TakeupModel(user=user_obj, query=query_obj, takenup=True)
        takeup_obj.save()
        resp = {
            'code' : 200,
            'phone' : query_obj.phone,
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
        user_key = data['user_key']
        id = data['id']
        query_obj = QueryModel.objects.filter(id=id).first()
        for x in query_obj:
            print x.id
        TakeupModel.objects.filter(query=query_obj).delete()
        if data['resolved'] == '1':
            user = UserModel.objects.get(key=user_key)
            user.points += 5
            user.save()
            QueryModel.objects.filter(id=id).delete()
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
                'id' : x.id,
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

@csrf_exempt
def CheckView(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode(encoding='UTF-8'))
        id = data['id']
        query_obj = QueryModel.objects.filter(id=id).first()
        taken = TakeupModel.objects.filter(query=query_obj).first()
        resp = {
            'code' : 200,
            'val' : taken.resolved
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def SetView(request):
    if request.method == 'POST':
        data = json.loads(request.body.decode(encoding='UTF-8'))
        if data['set'] == '1':
            id = data['id']
            query_obj = QueryModel.objects.filter(id=id).first()
            taken = TakeupModel.objects.get(query=query_obj)
            taken.resolved = True
            taken.save()
        else:
            id = data['id']
            query_obj = QueryModel.objects.filter(id=id).first()
            TakeupModel.objects.filter(query=query_obj).delete()
        resp = {
            'code': 200,
        }
    else:
        resp = {
            'code': 400,
        }

    return JsonResponse(resp, safe=False)

