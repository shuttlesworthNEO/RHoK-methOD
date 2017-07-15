# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import JsonResponse
import json
from django.views.decorators.csrf import csrf_exempt
from models import KeyModel
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
